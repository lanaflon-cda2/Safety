package com.example.SafetyAlerts.modeles;

import java.util.List;

public class MedicParPerson {
    private List<String> medications;
/*    private List<String> allergies;*/

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

/*    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }*/
}
