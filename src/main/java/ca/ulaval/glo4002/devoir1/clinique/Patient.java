package ca.ulaval.glo4002.devoir1.clinique;

import ca.ulaval.glo4002.devoir1.clinique.enumeration.VisibleSymptom;

import java.util.Objects;

public class Patient {
    private final String name;
    private final int gravity;
    private final VisibleSymptom visibleSymptom;

    public String getName() {
        return name;
    }

    public Patient(String name, int gravity, VisibleSymptom visibleSymptom) {
        this.name = name;
        this.gravity = gravity;
        this.visibleSymptom = visibleSymptom;
    }

    public int getGravity() {
        return gravity;
    }

    public VisibleSymptom getVisibleSymptom() {
        return visibleSymptom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getGravity() == patient.getGravity() && Objects.equals(getName(), patient.getName()) && getVisibleSymptom() == patient.getVisibleSymptom();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getGravity(), getVisibleSymptom());
    }
}
