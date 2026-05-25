package se.centernode.javaapitest.entity;

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
import se.centernode.javaapitest.enums.LinkType;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "statement_links")
public class StatementLink {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		Long id;
		
		@Column(name = "subject", nullable = false, unique = false)
		Long statementIdSource;
		
		@Enumerated(EnumType.STRING)
		@Column(name = "predicate", nullable = false, unique = false)
		LinkType linkType;
		
		@Column(name = "object", nullable = false, unique = false)
		Long statementIdPointer;		

		public StatementLink(Long sourceId, LinkType linkType, Long pointerId){
			this.statementIdSource = sourceId;
			this.linkType = linkType;
			this.statementIdPointer = pointerId;
		}
}
