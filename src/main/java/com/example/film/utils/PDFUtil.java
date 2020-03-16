package com.example.film.utils;

import com.example.film.doo.FilmDo;
import com.example.film.doo.TimeTableDo;
import com.example.film.entity.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/16 13:35
 */
public class PDFUtil {

    public static void createPDF(String filename, Order order, TimeTableDo timeTableDo, FilmDo filmDo, Integer peopleNum, String ticketSeatInfo) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("ticket of PDF");
            document.open();
            PdfPTable table = createTable(writer, order, timeTableDo, filmDo, peopleNum, ticketSeatInfo);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }


    public static PdfPTable createTable(PdfWriter writer, Order order, TimeTableDo timeTableDo, FilmDo filmDo, Integer peopleNum, String ticketSeatInfo) throws DocumentException, IOException {
        BaseFont bfComic = BaseFont.createFont("f:/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfComic, 12);

        PdfPTable table = new PdfPTable(3);//生成一个两列的表格
        PdfPCell cell;
        String[] imageSource = filmDo.getImage().split("/");
        Image img = Image.getInstance("D:\\IdeaWorkspace\\spring-framework-master\\film\\src\\main\\resources\\templates\\image\\" + imageSource[2]);
        img.scaleAbsolute(140, 177);
        cell = new PdfPCell(img, false);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" " + filmDo.getName() + "\n\n 类 型：" + filmDo.getTypeName() + "\n\n 片 长：" + filmDo.getLength() + "分钟\n\n 导 演：" + filmDo.getDirector() + "\n\n 主 演：" + filmDo.getActor(), font));
        cell.setColspan(2);//设置所占列数
        cell.setFixedHeight(100);//设置高度
        //cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        //cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("  ", font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(timeTableDo.getHallName() + " ：" + ticketSeatInfo, font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        cell = new PdfPCell(new Phrase("观影时间：" + sdf.format(timeTableDo.getStartTime()), font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("订单编号：" + order.getOrderNo(), font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("  ", font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("订单金额", font));
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("创建日期", font));
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("付款人数", font));
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        Font font1 = new Font(bfComic, 15);
        cell = new PdfPCell(new Phrase("￥" + order.getTotal(), font1));
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(sdf.format(order.getCreateTime()), font1));
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(peopleNum + "人", font1));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("  ", font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("取票码：" + order.getTicketCode(), font));
        cell.setColspan(3);//设置所占列数
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(0);
        table.addCell(cell);

        return table;
    }

}
