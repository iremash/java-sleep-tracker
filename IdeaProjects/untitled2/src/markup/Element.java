package markup;

public interface Element {
    void toMarkdown(StringBuilder sb);
    void toTypst(StringBuilder sb);
}
