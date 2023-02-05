package dev.jlkesh.stackoverflowdemo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public final class QuestionCreateDTO {
    private Integer id;
    private String title;
    private String content;

    public QuestionCreateDTO(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (QuestionCreateDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }

    @Override
    public String toString() {
        return "QuestionCreateDTO[" +
                "id=" + id + ", " +
                "title=" + title + ", " +
                "content=" + content + ']';
    }

}
