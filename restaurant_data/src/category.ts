export type CategoryID = string;

export class Category {
    id: CategoryID;
    displayName: string;
    searchKeywords: string[];
    implicates: CategoryID[];

    constructor(
        id: CategoryID,
        displayName: string,
        searchKeywords: string[] = [],
        implicates: CategoryID[] = []
    ) {
        this.id = id;
        this.displayName = displayName;
        this.searchKeywords = searchKeywords;
        this.implicates = implicates;
    }
}

export class CategoryDatabase {
    private _categories: Map<CategoryID, Category>;

    constructor() {
        this._categories = new Map();
    }

    addCategory(category: Category): CategoryDatabase {
        this._categories.set(category.id, category);

        return this;
    }

    getCategory(id: CategoryID): Category {
        if (!this._categories.has(id)) {
            throw new Error(`No category exists with id "${id}"`);
        }

        return this._categories.get(id)!;
    }

    private _canonicalizeCategory(category: Category | CategoryID): Category {
        if (category instanceof Category) {
            if (!this._categories.has(category.id))
                throw new Error(
                    `Category with id "${category.id}" does not exist in this database`
                );

            return category;
        }

        return this.getCategory(category);
    }

    getSuperCategories(category: Category | CategoryID): Category[] {
        let cat = this._canonicalizeCategory(category);

        return cat.implicates.map((id) => this.getCategory(id));
    }

    getAllCategories(): Category[] {
        return Array.from(this._categories.values());
    }

    getSubCategories(category: Category | CategoryID): Category[] {
        let cat = this._canonicalizeCategory(category);

        let out = new Set(
            this.getAllCategories().filter((n) =>
                n.implicates.includes(cat.id)
            )
        );

        for (let subCategory of out) {
            for (let subSubCategory of this.getSubCategories(subCategory)) {
                out.add(subSubCategory);
            }
        }

        return Array.from(out);
    }
}
