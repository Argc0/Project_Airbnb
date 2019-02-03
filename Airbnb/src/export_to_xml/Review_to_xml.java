package export_to_xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement
public class Review_to_xml {
	
	private int idreview;
	
	private String text;
	
	private String editor;
	
	public int getIdreview() {
    	return idreview;
    }
	
	@XmlAttribute
    public void setIdreview(int idreview) {
    	this.idreview = idreview;
    }
	
	public String getText() {
        return text;
    }
	
	@XmlElement
    public void setText(String text) {
        this.text = text;
    }
    
    public String getEditor() {
        return editor;
    }
    
    @XmlElement
    public void setEditor(String editor) {
        this.editor = editor;
    }
}
