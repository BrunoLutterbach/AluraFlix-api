create table videos (

        id bigint NOT NULL AUTO_INCREMENT,
        titulo varchar(255) NOT NULL,
        descricao varchar(255) NOT NULL,
        url varchar(255) NOT NULL,
        categoria_id bigint NOT NULL,
        foreign key (categoria_id) references categorias(id),
        ativo tinyint NOT NULL,

        PRIMARY KEY (id)

        )
