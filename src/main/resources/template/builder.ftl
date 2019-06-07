public class ${className}Build {

    private Builder builder;

    private ${className} ${classname};


    public ${className}Build insertInit() {
        this.${classname} = new ${className};
        #foreach($column in $columns)
            this.${className}.${column.method}(this.builder.${column.attribute});
        #end
        return this;
    }

    public ${className}Build updateInit() {
        this.${classname} = new ${className};
        #foreach($column in $columns)
            this.${className}.${column.method}(this.builder.${column.attribute});
        #end
        return this;
    }

    public ${className} get${classname}() {
        return this.${classname};
    }

    private ${className}Build(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        #foreach($column in $columns)
            private ${$column.type} ${column.attribute};
        #end

        #foreach($column in $columns)
            public Builder ${column.attribute}(${$column.type} ${column.attribute});
        #end

    }

}

