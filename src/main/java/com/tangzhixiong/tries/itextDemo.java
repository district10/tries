package com.tangzhixiong.tries;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// import com.lowagie.text.pdf.PdfCell;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by tzx on 2016/12/5.
 */
public class itextDemo {
    // [Java PDF Generation with IText](http://tutorials.jenkov.com/java-itext/index.html)
    public static void main(String[] args) {
        System.out.println("Hello");

        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("HelloWorld.pdf"));

            document.open();

            // paragrpha
            {
                document.add(new Paragraph("A Hello World PDF document."));
                // BaseFont bf = BaseFont.createFont( "STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                Font font = new Font(bf, 12, Font.NORMAL);
                document.add(new Paragraph("这是一个 PDF 文档。", font));

                Font font1 = new Font(Font.FontFamily.HELVETICA  , 25, Font.BOLD);
                Font font2 = new Font(Font.FontFamily.COURIER    , 18, Font.ITALIC | Font.UNDERLINE);
                Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 27);
                document.add(new Chunk(    "This is sentence 1. ", font1));
                document.add(new Phrase(   "This is sentence 2. ", font2));
                document.add(new Paragraph("This is sentence 3. ", font3));
            }

            // chunk: smallest possible "chunk" of text
            {
                document.add(new Chunk("This is sentence 1. "));
                document.add(new Chunk("This is sentence 2. "));
                document.add(new Chunk("This is sentence 3. "));
                document.add(new Chunk("This is sentence 4. "));
                document.add(new Chunk("This is sentence 5. "));
                document.add(new Chunk("This is sentence 6. "));
                document.add(new Chunk("This is sentence 7. "));
                document.add(new Chunk("This is sentence 8. "));
                document.add(new Chunk("This is sentence 9. "));
            }

            // phrase: add spacing between lines
            {
                document.add(new Phrase("This is sentence 1. "));
                document.add(new Phrase("This is sentence 2. "));
                document.add(new Phrase("This is sentence 3. "));
                document.add(new Phrase("This is sentence 4. "));
                document.add(new Phrase("This is sentence 5. "));
                document.add(new Phrase("This is sentence 6. "));
                document.add(new Phrase("This is sentence 7. "));
                document.add(new Phrase("This is sentence 8. "));
                document.add(new Phrase("This is sentence 9. "));
            }

            // phrase of chunks
            {
                Chunk chunk = new Chunk("This is a sentence.");

                // The default spacing is 1.5 times the font height.
                Phrase phrase = new Phrase(50); // line spacing, defaut is 72 dot/inch
                // 它会和上面一行 space 这么多距离。
                phrase.add(chunk);
                phrase.add(chunk);
                phrase.add(chunk);
                phrase.add(chunk);
                phrase.add(chunk);
                phrase.add(chunk);

                document.add(phrase);
            }

            // paragrph of chunks
            {
                Paragraph paragraph = new Paragraph();

                // spacing before/after
                {
                    System.out.println("Default spacing after: " + paragraph.getSpacingAfter());
                    paragraph.setSpacingAfter(50);
                    paragraph.setSpacingBefore(50);
                    System.out.println("Default spacing after: " + paragraph.getSpacingAfter());
                }

                // alignment
                {
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    // paragraph.setAlignment(Element.ALIGN_LEFT);
                    // paragraph.setAlignment(Element.ALIGN_RIGHT);
                }

                // indentation
                {
                    paragraph.setIndentationLeft(50);
                    paragraph.setIndentationRight(50);
                }

                // Paragraph paragraph = new Paragraph(50); // line spacing
                for(int i = 0; i < 10; i++) {
                    Chunk chunk = new Chunk("This is a sentence which is long " + i + ". ");
                    paragraph.add(chunk);
                }
                document.add(paragraph);
            }

            // chapter
            {
                Paragraph paragraph = new Paragraph();
                paragraph.add(new Phrase("This is a chapter."));
                Chapter chapter = new Chapter(paragraph, 1);

                Section section1 = chapter.addSection("This is section 1", 2);
                Section section2 = chapter.addSection("This is section 2", 2);

                document.add(chapter);
            }

            // anchor (link)
            {
                Paragraph paragraph = new Paragraph();
                paragraph.add(new Phrase("You can find the IText tutorial at "));

                Anchor anchor = new Anchor("click me to go.");
                anchor.setReference("http://tutorials.jenkov.com/java-itext/index.html");

                // Font af = anchor.getFont();
                // af.setSize(af.getSize()*2.0f);
                // anchor.setFont(af);

                paragraph.add(anchor);
                document.add(paragraph);


            }

            // internal anchor
            {
                Anchor anchor = new Anchor("Jump down to next paragraph");
                anchor.setReference("#linkTarget");
                Paragraph paragraph = new Paragraph();
                paragraph.add(anchor);
                document.add(paragraph);

                Anchor anchorTarget = new Anchor("This is the target of the link above");
                anchor.setName("linkTarget");
                Paragraph targetParagraph = new Paragraph();
                targetParagraph.setSpacingBefore(50);

                targetParagraph.add(anchorTarget);
                document.add(targetParagraph);
            }

            // list of items (ordered/unordered/roman style/greek style)
            {
                List orderedList = new List(List.ORDERED);
                orderedList.add(new ListItem("Item 1"));
                orderedList.add(new ListItem("Item 2"));
                orderedList.add(new ListItem("Item 3"));
                document.add(orderedList);

                List unorderedList = new List(List.UNORDERED);
                unorderedList.add(new ListItem("Item 1"));
                unorderedList.add(new ListItem("Item 2"));
                unorderedList.add(new ListItem("Item 3"));
                document.add(unorderedList);

                RomanList romanList = new RomanList();
                romanList.add(new ListItem("Item 1"));
                romanList.add(new ListItem("Item 2"));
                romanList.add(new ListItem("Item 3"));
                document.add(romanList);

                GreekList greekList = new GreekList();
                greekList.add(new ListItem("Item 1"));
                greekList.add(new ListItem("Item 2"));
                greekList.add(new ListItem("Item 3"));
                document.add(greekList);

                // IText has a special list implementation that uses the ZapfDingbats font.
                // It's constructor takes two parameters: The number of the symbol to use as item bullet,
                // and the indentation of the text after the bullet (space between bullet and text).
                ZapfDingbatsList zapfDingbatsList1 = new ZapfDingbatsList(40, 15);
                zapfDingbatsList1.add(new ListItem("Item 1"));
                zapfDingbatsList1.add(new ListItem("Item 2"));
                zapfDingbatsList1.add(new ListItem("Item 3"));
                document.add(zapfDingbatsList1);

                ZapfDingbatsList zapfDingbatsList2 = new ZapfDingbatsList(43, 30);
                zapfDingbatsList2.add(new ListItem("Item 1"));
                zapfDingbatsList2.add(new ListItem("Item 2"));
                zapfDingbatsList2.add(new ListItem("Item 3"));
                document.add(zapfDingbatsList2);

                ZapfDingbatsList zapfDingbatsList3 = new ZapfDingbatsList(47, 45);
                zapfDingbatsList3.add(new ListItem("Item 1"));
                zapfDingbatsList3.add(new ListItem("Item 2"));
                zapfDingbatsList3.add(new ListItem("Item 3"));
                document.add(zapfDingbatsList3);
            }

            // table
            {
                PdfPTable table = new PdfPTable(3); // 3 columns.
                table.setWidthPercentage(50);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
                PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
                PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
                cell2.setColspan(2);

                // column width
                float[] columnWidths = {2f, 1f, 1f};
                table.setWidths(columnWidths);

                // text-mode, composite mode
                /*
                {
                    PdfCell textModeCell = new PdfCell(new Paragraph("Text Mode"));
                    PdfCell compositeModeCell = new PdfCell();
                    compositeModeCell.addElement(new Paragraph("Composite Mode"));
                    table.addCell(new Paragraph("Text Mode"));
                }
                */

                table.addCell(cell1); table.addCell(cell2); table.addCell(cell3);
                document.add(table);
            }

            // image
            {
                Image image1 = Image.getInstance("img.jpg");
                document.add(image1);
                image1.scalePercent(20f); // 20%

                Image image2 = Image.getInstance("img.jpg");
                image2.scaleAbsolute(30f, 30f);
                document.add(image2);

                // Image image2 = Image.getInstance(new URL("http://whudoc.qiniudn.com/2016/img.jpg"));
            }

            // super/sub scripts, underline, strike through
            {
                Chunk normalText = new Chunk("Normal text at normal y-location. ");
                document.add(normalText);

                Chunk superScript = new Chunk("Superscript");
                superScript.setTextRise(5f);
                document.add(superScript);

                Chunk moreNormalText = new Chunk(". More normal y-location text. ");
                document.add(moreNormalText);

                Chunk subScript = new Chunk("Subscript");
                subScript.setTextRise(-5f);
                document.add(subScript);

                Chunk underline = new Chunk("Underline. ");
                underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
                document.add(underline);

                document.add(new Paragraph("   "));

                Chunk strikethrough = new Chunk("Strikethrough.");
                strikethrough.setUnderline(0.1f, 3f); //0.1 thick, 2 y-location
                document.add(strikethrough);
            }

            document.close(); // close the document, to flush all content
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
