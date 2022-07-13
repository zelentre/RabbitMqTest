package com.zne.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZNE
 * @since 2022/7/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mes implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String message;

}
