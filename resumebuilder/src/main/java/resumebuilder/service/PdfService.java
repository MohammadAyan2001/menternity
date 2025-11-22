package resumebuilder.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.color.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
// import com.itextpdf.kernel.pdf.PdfCanvas;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List; // iText List
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import org.springframework.stereotype.Service;
import resumebuilder.model.Resume;
import resumebuilder.model.Education;
import resumebuilder.model.Experience;
import resumebuilder.model.Project;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public ByteArrayInputStream generatePdf(Resume resume) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Add border rectangle to the page (A4 size)
        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.setLineWidth(1f);
        canvas.setStrokeColor(ColorConstants.BLACK);
        // Draw rectangle inset by 10 units from all sides
        canvas.rectangle(
            10, // x position from left
            10, // y position from bottom
            PageSize.A4.getWidth() - 20,  // width (page width minus left+right inset)
            PageSize.A4.getHeight() - 20  // height (page height minus top+bottom inset)
        );
        canvas.stroke();

        // Create Document with A4 page size and margins
        Document document = new Document(pdfDoc, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        document.setFont(font);

        // ----- HEADER -----
        document.add(new Paragraph(resume.getFullName())
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER));

        // Use dynamic city and state, fallback to empty if null
        String location = "";
        if (resume.getCity() != null && resume.getState() != null) {
            location = resume.getCity() + ", " + resume.getState();
        } else if (resume.getCity() != null) {
            location = resume.getCity();
        } else if (resume.getState() != null) {
            location = resume.getState();
        }
        document.add(new Paragraph(location).setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph(resume.getPhone() + " | " + resume.getEmail())
                .setTextAlignment(TextAlignment.CENTER));

        // Dynamic LinkedIn & GitHub URLs, show only if present
        if (resume.getLinkedIn() != null && !resume.getLinkedIn().isBlank()) {
            document.add(new Paragraph("LinkedIn: " + resume.getLinkedIn())
                    .setTextAlignment(TextAlignment.CENTER));
        }
        if (resume.getGithub() != null && !resume.getGithub().isBlank()) {
            document.add(new Paragraph("GitHub: " + resume.getGithub())
                    .setTextAlignment(TextAlignment.CENTER));
        }

        document.add(new Paragraph("\n"));

        // ----- SUMMARY -----
        addSectionHeader(document, "Professional Summary");
        document.add(new Paragraph(resume.getSummary() != null ? resume.getSummary() : ""));
        addSpacer(document);

        // ----- TECHNICAL SKILLS -----
        addSectionHeader(document, "Technical Skills");
        if (resume.getSkills() != null && !resume.getSkills().isEmpty()) {
            List skillsList = new List().setSymbolIndent(12).setListSymbol("• ");
            for (String skill : resume.getSkills()) {
                skillsList.add(new ListItem(skill));
            }
            document.add(skillsList);
        } else {
            document.add(new Paragraph("No technical skills listed."));
        }
        addSpacer(document);

        // ----- SOFT SKILLS -----
        if (resume.getSoftSkills() != null && !resume.getSoftSkills().isEmpty()) {
            addSectionHeader(document, "Soft Skills");
            List softSkillsList = new List().setSymbolIndent(12).setListSymbol("• ");
            for (String softSkill : resume.getSoftSkills()) {
                softSkillsList.add(new ListItem(softSkill));
            }
            document.add(softSkillsList);
            addSpacer(document);
        }

        // ----- EDUCATION -----
        addSectionHeader(document, "Education");
        if (resume.getEducation() != null && !resume.getEducation().isEmpty()) {
            for (Education edu : resume.getEducation()) {
                document.add(new Paragraph(edu.getDegree() + " in " + edu.getFieldOfStudy() + " - " + edu.getInstitution()));
                document.add(new Paragraph(edu.getStartDate() + " - " + edu.getEndDate() + " | Grade: " + edu.getGrade()));
                addSpacer(document);
            }
        } else {
            document.add(new Paragraph("No education details available."));
            addSpacer(document);
        }

        // ----- EXPERIENCE -----
        addSectionHeader(document, "Experience");
        if (resume.getExperience() != null && !resume.getExperience().isEmpty()) {
            for (Experience exp : resume.getExperience()) {
                document.add(new Paragraph(exp.getJobTitle() + " at " + exp.getCompanyName() + " - " + exp.getLocation())
                        .setBold());
                document.add(new Paragraph(exp.getStartDate() + " - " + exp.getEndDate()));

                if (exp.getResponsibilities() != null && !exp.getResponsibilities().isEmpty()) {
                    List respList = new List().setSymbolIndent(12).setListSymbol("• ");
                    for (String resp : exp.getResponsibilities()) {
                        respList.add(new ListItem(resp));
                    }
                    document.add(respList);
                }
                addSpacer(document);
            }
        } else {
            document.add(new Paragraph("No experience details available."));
            addSpacer(document);
        }

        // ----- PROJECTS -----
        addSectionHeader(document, "Projects");
        if (resume.getProjects() != null && !resume.getProjects().isEmpty()) {
            for (Project project : resume.getProjects()) {
                document.add(new Paragraph(project.getTitle()).setBold().setItalic());
                document.add(new Paragraph(project.getDescription()));
                if (project.getTechnologies() != null && !project.getTechnologies().isEmpty()) {
                    document.add(new Paragraph("Technologies: " + String.join(", ", project.getTechnologies())));
                }
                if (project.getLink() != null && !project.getLink().isBlank()) {
                    document.add(new Paragraph("Link: " + project.getLink()));
                }
                addSpacer(document);
            }
        } else {
            document.add(new Paragraph("No projects listed."));
            addSpacer(document);
        }

        // ----- ACHIEVEMENTS / ATTRIBUTES -----
        addSectionHeader(document, "Professional Attributes");
        if (resume.getAchievements() != null && !resume.getAchievements().isEmpty()) {
            List achList = new List().setSymbolIndent(12).setListSymbol("• ");
            for (String ach : resume.getAchievements()) {
                achList.add(new ListItem(ach));
            }
            document.add(achList);
        } else {
            document.add(new Paragraph("No professional attributes listed."));
        }

        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void addSectionHeader(Document document, String title) {
        document.add(new Paragraph(title)
                .setBold()
                .setFontSize(13)
                .setUnderline()
                .setMarginBottom(5));
    }

    private void addSpacer(Document document) {
        document.add(new Paragraph("\n"));
    }
}
