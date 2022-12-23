package com.cybersoft.food_project.entity.id;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FoodMaterialId implements Serializable {

    @Column(name = "id_food")
    private int idFood;
    @Column(name = "id_material")
    private int idMaterial;
    public FoodMaterialId(int idFood, int idMaterial) {
        this.idFood = idFood;
        this.idMaterial = idMaterial;
    }

    public FoodMaterialId() {
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }
}
