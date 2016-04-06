package br.com.erudio.vo;

public class GreetingVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	final Long id;
    final String content;

	public GreetingVO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
