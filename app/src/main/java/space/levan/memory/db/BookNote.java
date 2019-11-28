package space.levan.memory.db;


import androidx.annotation.Nullable;

import java.util.List;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class BookNote {

    private long bookId;
    private String bookIsbn;
    private String bookTitle;
    @Nullable
    private List bookAuthors;
    @Nullable
    private String bookAuthorInfo;
    @Nullable
    private String bookCover;
    @Nullable
    private String bookSummary;
    @Nullable
    private String coverSmall;
    @Nullable
    private String coverMedium;
    @Nullable
    private String coverLarge;
    @Nullable
    private String noteQuotation;
    private int notePageNum;
    @Nullable
    private String noteContent;
    private long createTime;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Nullable
    public List getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(@Nullable List bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    @Nullable
    public String getBookAuthorInfo() {
        return bookAuthorInfo;
    }

    public void setBookAuthorInfo(@Nullable String bookAuthorInfo) {
        this.bookAuthorInfo = bookAuthorInfo;
    }

    @Nullable
    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(@Nullable String bookCover) {
        this.bookCover = bookCover;
    }

    @Nullable
    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(@Nullable String bookSummary) {
        this.bookSummary = bookSummary;
    }

    @Nullable
    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(@Nullable String coverSmall) {
        this.coverSmall = coverSmall;
    }

    @Nullable
    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(@Nullable String coverMedium) {
        this.coverMedium = coverMedium;
    }

    @Nullable
    public String getCoverLarge() {
        return coverLarge;
    }

    public void setCoverLarge(@Nullable String coverLarge) {
        this.coverLarge = coverLarge;
    }

    @Nullable
    public String getNoteQuotation() {
        return noteQuotation;
    }

    public void setNoteQuotation(@Nullable String noteQuotation) {
        this.noteQuotation = noteQuotation;
    }

    public int getNotePageNum() {
        return notePageNum;
    }

    public void setNotePageNum(int notePageNum) {
        this.notePageNum = notePageNum;
    }

    @Nullable
    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(@Nullable String noteContent) {
        this.noteContent = noteContent;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
