package guru.springframework.sfgpetclinic.model.enums;

public enum OwnerType {

    INDIVIDUAL(0), COMPANY(1);

    private final int type;

    private OwnerType(final int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
