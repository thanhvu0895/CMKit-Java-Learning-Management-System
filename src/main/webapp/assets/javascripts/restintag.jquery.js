/**!
 * Rest-In-Tag 0.1.0
 * https://github.com/KhaledElAnsari/RESTInTag/
 *
 * Inspired by Favre Anael works :
 * https://github.com/Ifnot/RestfulizerJs
 *
 * Copyright 2017 Khaled Al-Ansari
 * Released under the MIT license
 * https://github.com/KhaledElAnsari/RESTInTag/blob/master/LICENSE
 */

var rest = function(options, successCB, errorCB) {
    var defaultOptions = $.extend({
        async: true,
        parse: false,
        target: null,
        method: "GET",
        headers: {},
        timeout: 0,
        data: {},
        disable: true,
        once: false
    }, options);

    return $(this).each(function () {
        var $this = $(this);
        var thisOptions = $.extend({}, defaultOptions);
        var isOnce = $this.data("once") || thisOptions.once;
        var isDisabled = $this.data("disabled") || thisOptions.disable;
        thisOptions.method = $this.data("method") || thisOptions.method;
        thisOptions.target = $this.data("target") || thisOptions.target;

        $this.removeAttr('href');

        if (thisOptions.parse) {
            var paramsIndex = thisOptions.target.indexOf("?");
            var hasParams = (paramsIndex > -1);

            if (hasParams) {
                var params = thisOptions.target.substr(paramsIndex + 1).split('#')[0].split('&');
                thisOptions.target = thisOptions.target.substr(0, paramsIndex);

                $.map(params, function(val, i) {
                    var pair = params[i].split('=');
                    thisOptions.data[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1]);
                });
            }
        }

        if(typeof thisOptions.data !== "string" && thisOptions.method !== "GET") thisOptions.data = JSON.stringify(thisOptions.data);
        
        $this.on("click", function(event) {
            event.preventDefault();
            event.stopPropagation();
            if(isOnce || isDisabled) {
                $this.attr("disabled", isOnce || isDisabled);
                $this.css({
                    "pointer-events": "none",
                    "cursor": "default"
                });
            }
            $.ajax({
                async: thisOptions.async,
                url: thisOptions.target,
                method: thisOptions.method,
                headers: thisOptions.headers,
                data: thisOptions.data,
                timeout: thisOptions.timeout,
                success: function(data, textStatus, jqXHR) { if(successCB) successCB(data, textStatus, jqXHR); },
                error: function(jqXHR, textStatus, errorThrown) {
                    if(errorCB) {
                        errorCB(jqXHR, textStatus, errorThrown);
                    }
                    else {
                        console.log(jqXHR);
                    }
                }
            }).done(function() {
                if(!isOnce && isDisabled) {
                    $this.attr("disabled", isDisabled);
                    $this.css({
                        "pointer-events": "",
                        "cursor": ""
                    });
                }
                // else if(isOnce) $this.off("click");
            });
        });
    });
};

if (typeof exports !== 'undefined') {
  if (typeof module !== 'undefined' && module.exports) {
    exports = module.exports = rest;
  }
  exports.rest = rest;
}
else {
    (function ($) {
        $.fn.restintag = rest;
    })(jQuery);
}
