package common.dto;

public class SkillFullDto {

    private int idSkill;

    private String nameSkill;

    public SkillFullDto(int idSkill, String nameSkill){
        this.idSkill = idSkill;
        this.nameSkill = nameSkill;
    }

    public String getNameSkill() {
        return nameSkill;
    }

    public void setNameSkill(String nameSkill) {
        this.nameSkill = nameSkill;
    }
}
