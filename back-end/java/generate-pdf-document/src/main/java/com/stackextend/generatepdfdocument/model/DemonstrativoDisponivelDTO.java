package com.stackextend.generatepdfdocument.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Builder
@Generated
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DemonstrativoDisponivelDTO {

  private String produto;
  private String numeroContrato;
  private String dataLiberacao;
  private String situacao;

}
