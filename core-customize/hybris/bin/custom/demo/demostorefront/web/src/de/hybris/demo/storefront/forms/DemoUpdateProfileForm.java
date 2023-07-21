package de.hybris.demo.storefront.forms;

public class DemoUpdateProfileForm {
    private String titleCode;
    private String firstName;
    private String lastName;
    private Boolean permanentSustainable;

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getPermanentSustainable() {
        return permanentSustainable;
    }

    public void setPermanentSustainable(Boolean permanentSustainable) {
        this.permanentSustainable = permanentSustainable;
    }
}
