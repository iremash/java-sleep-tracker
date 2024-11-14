package markup;

import java.util.List;

public class Strikeout extends Main{
    protected Strikeout(List<Element> elements) {
        this.elements = elements;
        this.markdownSymbol = "~";
        this.typstOpenSymbol= "#strike[";
        this.typstCloseSymbol = "]";
    }

}
