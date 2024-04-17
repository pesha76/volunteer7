<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div>
        <form action="/anketa/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">ФИО</label>
                <input type="text" required class="form-control"
                       id="fio" name="fio">
            </div>

            <div class="form-group">
                <label for="productName">Почему вы хотете стать организатором?</label>
                <input type="text" required class="form-control"
                       id="description" name="description">
            </div>
            <div class="form-group">
                <label for="searchCity">Ваш телефон</label>
                <input type="text" required class="form-control"
                       id="phone" name="phone">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Отправить заявку</button>
        </form>
    </div>
</@t.template>