package space.levan.memory.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import space.levan.memory.bean.realm.Book;

/**
 * Created by WangZhiYao on 2017/5/4.
 */

public class JxlUtils {
    public static void exportExcel(Context context) {
        /* 创建文件 */
        WritableWorkbook mWorkbook;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        String fileName = "Memory" + "_" + dateFormat.format(curDate) + ".xls";
        try {
            mWorkbook = Workbook.createWorkbook(new File(context.getFilesDir()
                    + "/"
                    + fileName));

            WritableSheet mSheet = mWorkbook.createSheet("书籍", 0);

            String[] strTitle = {"书名", "作者", "原作名",
                    "副标题", "译者", "出版社",
                    "出版日期", "封面地址", "页数",
                    "ISBN", "简介"};

            /* 自动列宽 */
            //CellView cellView = new CellView();
            //cellView.setAutosize(true);

            /* 设置标题栏 */
            for (int i = 0; i < strTitle.length; i++) {
                Label titleLabel = new Label(i, 0, strTitle[i], getTitleFormat());
                mSheet.addCell(titleLabel);
                //mSheet.setColumnView(i, cellView);
            }

            /* 设置内容 */
            Realm realm = Realm.getDefaultInstance();
            RealmQuery<Book> bookRealmQuery = realm.where(Book.class);
            RealmResults<Book> bookRealmResults = bookRealmQuery.findAll();
            ArrayList<String> resultList;

            for (int x = 0; x < bookRealmResults.size(); x++) {
                Book mBook = bookRealmResults.get(x);
                resultList = new ArrayList<>();
                resultList.add(mBook.title);
                resultList.add(mBook.authors);
                resultList.add(mBook.origin_title);
                resultList.add(mBook.subtitle);
                resultList.add(mBook.translators);
                resultList.add(mBook.publisher);
                resultList.add(mBook.pubdate);
                resultList.add(mBook.image);
                resultList.add(mBook.pages);
                resultList.add(mBook.isbn13);
                resultList.add(mBook.summary);
                int k = 0;

                for (String bookInfo : resultList) {
                    Label contentLabel = new Label(k, x + 1, bookInfo, getContentFormat());
                    mSheet.addCell(contentLabel);
                    mSheet.setColumnView(k, bookInfo.length() + 8);
                    k++;
                }
            }

            mWorkbook.write();
            mWorkbook.close();
            Toast.makeText(context, "导出Excel成功",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "导出Excel失败",
                    Toast.LENGTH_SHORT).show();
        } catch (WriteException e) {
            e.printStackTrace();
            Toast.makeText(context, "导出Excel失败",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private static WritableCellFormat getTitleFormat() throws WriteException {
        /* 标题栏字体，大小，加粗，边框，背景色，居中，自动换行 */
        WritableFont mFont = new WritableFont(WritableFont
                .createFont("宋体"), 10, WritableFont.BOLD);
        WritableCellFormat mCellFormat = new WritableCellFormat(mFont);
        mCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        mCellFormat.setBackground(Colour.GRAY_25);
        mCellFormat.setAlignment(Alignment.CENTRE);
        mCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        mCellFormat.setWrap(true);

        return mCellFormat;
    }

    private static WritableCellFormat getContentFormat() throws WriteException {
        WritableFont mFont = new WritableFont(WritableFont
                .createFont("宋体"), 10);
        WritableCellFormat mCellFormat = new WritableCellFormat(mFont);
        mCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        mCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

        return mCellFormat;
    }
}
