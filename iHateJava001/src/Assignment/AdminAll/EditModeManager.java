/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment.AdminAll;

public class EditModeManager {
    private static boolean isInEditMode = false;

    public static void enterEditMode() {
        isInEditMode = true;
    }

    public static void exitEditMode() {
        isInEditMode = false;
    }

    public static boolean isInEditMode() {
        return isInEditMode;
    }
}
