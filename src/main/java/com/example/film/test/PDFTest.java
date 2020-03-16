package com.example.film.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/16 9:17
 */
public class PDFTest {

    public static void main(String[] args) throws Exception {

        PDFTest pdf = new PDFTest();
        String filename = "F:/testTable3.pdf";
        pdf.createPDF(filename);
        System.out.println("打印完成");

    }
    /*public void createPDF(String filename) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            document.add(new Paragraph("Hello World!"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }*/


    public static PdfPTable createTable(PdfWriter writer) throws DocumentException, IOException{
        BaseFont bfComic = BaseFont.createFont("f:/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//支持中文
        Font font = new Font(bfComic, 12);

        PdfPTable table = new PdfPTable(2);//生成一个两列的表格
        PdfPCell cell;
        int size = 15;
        Image img = Image.getInstance("D:\\IdeaWorkspace\\spring-framework-master\\film\\src\\main\\resources\\templates\\image\\a24f188d-2a97-436f-a2ba-8e84d2ec9167.jpg");
        cell = new PdfPCell(img);
        table.addCell(img);
        cell = new PdfPCell(new Phrase("电影",font));
        //cell.setColspan(2);//设置所占列数
        cell.setFixedHeight(size*2);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("场次",font));
        cell.setColspan(2);//设置所占列数
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("大厅",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("座位",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("大厅",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("座位",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        return table;
    }

    public void createPDF(String filename) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            PdfPTable table = createTable(writer);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

}
