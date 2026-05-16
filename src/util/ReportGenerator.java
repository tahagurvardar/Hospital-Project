package util;

import model.Appointment;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.util.ArrayList;

public class ReportGenerator {

    public static void exportAppointmentsToPDF(ArrayList<Appointment> appointments) {

        try {

            PDDocument document = new PDDocument();

            PDPage page = new PDPage();

            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font textFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            contentStream.beginText();
            contentStream.setFont(titleFont, 18);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Hospital Appointment Report");
            contentStream.endText();

            int y = 710;

            contentStream.beginText();
            contentStream.setFont(textFont, 11);
            contentStream.newLineAtOffset(50, y);

            contentStream.showText("ID | Patient | Doctor | Date | Time | Status");
            contentStream.newLineAtOffset(0, -20);

            for (Appointment appointment : appointments) {

                String line =
                        appointment.getId() + " | " +
                                appointment.getPatientName() + " | " +
                                appointment.getDoctorName() + " | " +
                                appointment.getDate() + " | " +
                                appointment.getTime() + " | " +
                                appointment.getStatus();

                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -18);
            }

            contentStream.endText();

            contentStream.close();

            File file = new File("Appointment_Report.pdf");

            document.save(file);

            document.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}