function newXMLHttpRequest() {
    var xmlreq = false;

    if (window.XMLHttpRequest) {

        // Создадим XMLHttpRequest объект для не-Microsoft браузеров
        xmlreq = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        // Создадим XMLHttpRequest с помощью MS ActiveX
        try {
            // Попробуем создать XMLHttpRequest для поздних версий
            // Internet Explorer

            xmlreq = new ActiveXObject(XML_HTTP_REQUEST_FOR_LAST_IE);

        } catch (e1) {

            // Не удалось создать требуемый ActiveXObject

            try {
                // Пробуем вариант, который поддержат более старые версии
                //  Internet Explorer

                xmlreq = new ActiveXObject(XML_HTTP_REQUEST_FOR_EARLY_IE);

            } catch (e2) {

                // Не в состоянии создать XMLHttpRequest с помощью ActiveX
            }
        }
    }

    return xmlreq;
}