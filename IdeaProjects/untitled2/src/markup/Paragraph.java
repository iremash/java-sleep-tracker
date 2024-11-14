package markup;

import java.util.List;

public class Paragraph extends Main {
    protected Paragraph(List<Element> elements) {
        this.elements = elements;
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        for (Element element : elements) {
            element.toMarkdown(sb);
        }
    }
}
