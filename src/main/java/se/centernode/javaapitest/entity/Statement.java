package se.centernode.javaapitest.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.centernode.javaapitest.enums.State;
import se.centernode.javaapitest.enums.StatementType;


@Entity
@Getter
@Setter
@ToString	
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "statements")
public class Statement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sid", nullable = false, unique = false)
	private String sid;
	
	@Column(name = "type", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private StatementType type;
	
	@Column(name = "version", nullable = false, unique = false)
	private Integer version;
	
	@Column(name = "author", nullable = false, unique = false)
	private String author;
	
	@Column(name = "state", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Column(name = "created_time", nullable = true, unique = false)
	private Long createdTime;

	@Column(name = "text", nullable = false, unique = false)
	private String text;
	
	//List<String> references = new ArrayList<String>();
	
	/**
	 * Used when creating a new statement.
	 * @param type
	 * @param author
	 * @param state
	 * @param text
	 */
	public Statement(StatementType type, String author, State state, String text) {
		sid = UUID.randomUUID().toString();
		this.type = type;
		this.version = 1;
		this.author = author;
		this.state = state;
		this.createdTime = System.currentTimeMillis();
		this.text = text;
	}
	
	/**
	 * Used when updating a statement. 
	 * Increments version and updates timestamp.
	 * Is NOT automatically saved.
	 * 
	 * @param oldStatment
	 * @param newText
	 */
	public Statement(Statement oldStatment, String newText) {
		this.sid = oldStatment.getSid();
		this.type = oldStatment.getType();
		this.version = oldStatment.getVersion() + 1;
		this.author = oldStatment.getAuthor();
		this.state = oldStatment.getState();
		this.createdTime = System.currentTimeMillis();
		this.text = newText;
	}

}