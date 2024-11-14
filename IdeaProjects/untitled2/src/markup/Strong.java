package markup;

import java.util.List;

public class Strong extends Main{
    protected Strong(List<Element> elements) {
        this.elements = elements;
        this.markdownSymbol = "__";
        this.typstOpenSymbol= "#strong[";
        this.typstCloseSymbol = "]";
    }

}
