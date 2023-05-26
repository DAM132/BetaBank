/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author DAM118
 */
public  enum EstadoLaboral {
    EMPLEADO,DESEMPLEADO, AUTÓNOMO, PENSIONISTA, RENTISTA, ESTUDIANTE, RESPONSABLE_DEL_HOGAR;
    
    public static final List<EstadoLaboral> ESTADOS_PAREJA = Arrays.asList(DESEMPLEADO, ESTUDIANTE, RESPONSABLE_DEL_HOGAR);
    public static final List<EstadoLaboral> ESTADOS_ECONOMICOS = Arrays.asList(EMPLEADO, RENTISTA, PENSIONISTA);
}
