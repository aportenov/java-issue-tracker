package softuni.domains.dto;

import java.util.Date;

public class IssueDto {

    private Long id;

    private String name;

    private String priority;

    private String status;

    private Date creationDate;

    private UserDto author;

    public IssueDto() {
    }

    public IssueDto(String name, String priority, String status, UserDto author) {
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }
}
