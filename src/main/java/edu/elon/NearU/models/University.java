package edu.elon.NearU.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * University.java
 * @author Dylan Arone
 * @version 2026.10.25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University {
    private Long id;
    private String name;
    private String domain;     // e.g., "harvard.edu"
    private Location location; // Custom class holding coordinates and other info
}
