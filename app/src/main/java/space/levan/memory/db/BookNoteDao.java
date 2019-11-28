package space.levan.memory.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

/**
 * @author WangZhiYao
 * @date 2019/6/29
 */
@Dao
public interface BookNoteDao {

    @Query(
            "SELECT BookInfo.id AS bookId, " +
                    "BookInfo.isbn AS bookIsbn, " +
                    "BookInfo.title AS bookTitle, " +
                    "BookInfo.cover AS bookCover, " +
                    "BookInfo.authors AS bookAuthors, " +
                    "BookInfo.author_info AS bookAuthorInfo, " +
                    "BookInfo.summary AS bookSummary, " +
                    "BookCover.small AS coverSmall, " +
                    "BookCover.medium AS coverMedium, " +
                    "BookCover.large AS coverLarge, " +
                    "Note.quotation AS noteQuotation, " +
                    "Note.page_num AS notePageNum, " +
                    "Note.content AS noteContent, " +
                    "Note.create_time AS createTime " +
                    "FROM " +
                    "BookInfo " +
                    "INNER JOIN BookCover ON BookCover.isbn = BookInfo.isbn " +
                    "INNER JOIN Note ON Note.isbn = BookInfo.isbn"
    )
    LiveData<List<BookNote>> queryAllNote();
}