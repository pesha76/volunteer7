<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои участия</h1>
    <hr>
    <div class="card-columns">
        <#if favoriteproducts?has_content>
            <#list favoriteproducts as favoriteproduct>
                <a href="/favorite/${favoriteproduct.id}" class="product-link">
                    <div class="card text-white bg-dark my-3 favorite_product">
                        <div class="card-top">
                            <form action="/favariteproduct/delete/${favoriteproduct.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-danger m-3">
                                    Отказаться от участия
                                </button>
                            </form>
                        </div>
                        <img src="/images/${favoriteproduct.previewImageId}" class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${favoriteproduct.title}</h5>
                            <p class="card-text">${favoriteproduct.price} Ч</p>
                        </div>
                        <div class="card-footer">
                            ${favoriteproduct.city}
                        </div>
                    </div>
                </a>
            </#list>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#if>
    </div>
</@t.template>