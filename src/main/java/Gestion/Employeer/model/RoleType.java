package Gestion.Employeer.model;

public enum RoleType {
    AGENT_SIMPLE(1),
    DISTRICT(2),
    REGION(3),
    MINISTERE(4),
    ADMIN(5);

    private final int niveau;

    RoleType(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}
