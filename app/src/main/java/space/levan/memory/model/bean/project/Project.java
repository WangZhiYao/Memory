package space.levan.memory.model.bean.project;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Project entity
 *
 * @author WangZhiYao
 * @date 2017/12/1
 */

public class Project extends RealmObject {

    private String name;
    private String status;
    private String start_time;
    private String end_time;
    private String notes;
    private String member;
    private RealmList<Document> documents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public RealmList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(RealmList<Document> documents) {
        this.documents = documents;
    }
}