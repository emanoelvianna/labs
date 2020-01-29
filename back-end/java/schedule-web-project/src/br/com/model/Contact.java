package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "SEQ_CNV_OID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CNV_OID", sequenceName = "SEQ_CNV_OID", allocationSize = 1)
	private Long id;
	private String name;
	private String phone;
	private String email;
}
