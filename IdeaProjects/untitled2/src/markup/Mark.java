package markup;

import java.util.List;


public abstract class Mark implements Element {
    List<Element> elements;
    String markdownSymbol;
    String typstOpenSymbol = "";
    String typstCloseSymbol = "";

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownSymbol);
        for (Element element : elements) {
            element.toMarkdown(sb);
        }
        sb.append(markdownSymbol);
    }

    @Override
    public void toTypst(StringBuilder sb) {
        sb.append(typstOpenSymbol);
        for (Element element : elements) {
            element.toTypst(sb);
        }
        sb.append(typstCloseSymbol);
    }
}
