
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name;
	private String	description;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	// Relationships ----------------------------------------------------------

	private List<Servicce>	servicces;
	private Category		categoryFather;
	private List<Category>	categoriesChildren;


	@Valid
	@ManyToOne(optional = true)
	public Category getCategoryFather() {
		return this.categoryFather;
	}

	public void setCategoryFather(final Category categoryFather) {
		this.categoryFather = categoryFather;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "categoryFather")
	public List<Category> getCategoriesChildren() {
		return this.categoriesChildren;
	}

	public void setCategoriesChildren(final List<Category> categoriesChildren) {
		this.categoriesChildren = categoriesChildren;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	public List<Servicce> getServicces() {
		return this.servicces;
	}

	public void setServicces(final List<Servicce> servicces) {
		this.servicces = servicces;
	}
}
