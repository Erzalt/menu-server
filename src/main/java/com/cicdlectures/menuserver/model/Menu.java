package com.cicdlectures.menuserver.model;

import java.util.Set;

import com.cicdlectures.menuserver.dto.MenuDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  private Set<Dish> dishes;

  public Menu(Long id, String name, Set<Dish> dishes) {
    this.id = id;
    this.name = name;
    this.dishes = dishes;
  }

  public static Menu fromDto(MenuDto menuDto) {
    return new Menu()
      .setId(menuDto.getId())
      .setName(menuDto.getName())
      .setDishes(Dish.fromDtoSet(menuDto.getDishes()));
  }
}
