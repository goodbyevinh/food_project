package com.cybersoft.food_project.entity;

import com.cybersoft.food_project.entity.id.FoodMaterialId;

import javax.persistence.*;

@Entity(name = "food_material")
@IdClass(FoodMaterialId.class)
public class FoodMaterialEntity {

    @Id
    private int idFood;
    @Id
    private int idMaterial;

    @ManyToOne
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name = "id_material", insertable = false, updatable = false)
    private MaterialEntity material;

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

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }
}
