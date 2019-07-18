/**
 * @title 简洁实用的城市选择插件
 * @author IIInsomnia
 *
 * var cityPicker = new IIInsomniaCityPicker({
 *      data: cityData,                城市数据
 *      target: '#targetID',           目标元素ID（如：文本框，该值为城市名称）
 *      valType: 'k-v',                隐藏域的值类型（取值有：'k' => 只存ID，'k-v' => 存ID和名称，格式为：id-name）
 *      hideCityInput: '#hideID'       城市隐藏域ID
 *      hideProvinceInput: '#hideID'   省份隐藏域ID（如果不需要存省份，此项可以省略）
 *      callback: function(city_id){   回调函数，选择城市后调用，city_id为选择的城市的ID
 *          alert(city_id);
 *      }
 * });
 *
 * cityPicker.init();
 */
;var IIInsomniaCityPicker = function(options){
    this.template = $('<div class="IIInsomnia-city-picker" id="IIInsomnia_city_picker"><div class="IIInsomnia-hot-wrap"><p>热门城市</p><ul id="IIInsomnia_hot_city"></ul></div><div class="line"></div><div class="IIInsomnia-wrap"></div></div>');
    this.hot_city = $('#IIInsomnia_hot_city', this.template);
    this.province_wrap = $('#IIInsomnia_province_wrap', this.template);
    this.settings = {
        "data": options.data,
        "target": $(options.target),
        "valType": options.valType || 'k',
        "multiple": options.multiple || false,
        "hideProvinceInput": options.hideProvinceInput ? $(options.hideProvinceInput) : false,
        "hideCityInput": options.hideCityInput ? $(options.hideCityInput) : false,
        "callback": options.callback || '',
    };
};

IIInsomniaCityPicker.prototype = {
    init: function(){
        var that = this;

        $(window).click(function(event) {
            /* Act on the event */
            that.template.remove();
        });

        that.settings.target.attr('readonly', true);

        that.targetEvent();
    },

    buildCityPicker: function(){
        var that = this;

        that.buildHotCityTpl();
        that.cityEvent();
        that.cleanBtnEvent();
    },

    buildHotCityTpl: function(){
        var that = this;

        var hot_city = that.settings.data.hot;
        var hot_city_html = '';

        for(var i = 0, len = hot_city.length; i < len; i++){
            hot_city_html += '<li class="IIInsomnia-hot-city" data-id="' + hot_city[i]['id'] + '" data-name="' + hot_city[i]['name'] + '" data-pid="' + hot_city[i]['pid'] + '" data-pname="' + hot_city[i]['pname'] + '">' + hot_city[i]['name'] + '</li>';
        }

        that.hot_city.html(hot_city_html);
    },

    cityEvent: function(){
        var that = this;

        that.hot_city.on('click', '.IIInsomnia-hot-city', function(event) {
            event.preventDefault();
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            var cid = _this.data('id');
            var cname = _this.data('name');

            that.settings.target.val(cname);

            if(that.settings.hideCityInput){
                if(that.settings.valType == 'k-v'){
                    that.settings.hideCityInput.val(cid + '-' + cname);
                }else{
                    that.settings.hideCityInput.val(cid);
                }
            }

            if(that.settings.hideProvinceInput){
                var pid = _this.data('pid');
                var pname = _this.data('pname');
                if(that.settings.valType == 'k-v'){
                    that.settings.hideProvinceInput.val(pid + '-' + pname);
                }else{
                    that.settings.hideProvinceInput.val(pid);
                }
            }

            that.template.remove();

            if(that.settings.callback) that.settings.callback(cid);

            return false;
        });

        that.province_wrap.on('click', '.IIInsomnia-city', function(event) {
            event.preventDefault();
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            var cid = _this.data('id');
            var cname = _this.data('name');

            that.settings.target.val(cname);

            if(that.settings.hideCityInput){
                if(that.settings.valType == 'k-v'){
                    that.settings.hideCityInput.val(cid + '-' + cname);
                }else{
                    that.settings.hideCityInput.val(cid);
                }
            }

            if(that.settings.hideProvinceInput){
                var pele = _this.parent().parent();
                var pid = pele.data('id');
                var pname = pele.data('name');
                if(that.settings.valType == 'k-v'){
                    that.settings.hideProvinceInput.val(pid + '-' + pname);
                }else{
                    that.settings.hideProvinceInput.val(pid);
                }
            }

            that.template.remove();

            if(that.settings.callback) that.settings.callback(cid);

            return false;
        });
    },

    cleanBtnEvent: function(){
        var that = this;

        that.province_wrap.on('click', '#IIInsomnia_clean_btn', function(event){
            event.preventDefault();
            event.stopPropagation();
            /* Act on the event */
            that.settings.target.val('');

            if(that.settings.hideProvinceInput){
                that.settings.hideProvinceInput.val('');
            }

            if(that.settings.hideCityInput){
                that.settings.hideCityInput.val('');
            }

            that.template.remove();

            if(that.settings.callback) that.settings.callback(0);

            return false;
        });
    },

    targetEvent: function(){
        var that = this;

        that.settings.target.click(function(event){
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            that.buildCityPicker();

            var offset = _this.offset();
            var top = offset.top + _this.outerHeight() + 15;

            that.template.css({
                'left': offset.left,
                'top': top
            });

            $('body').append(that.template);

            return false;
        });
    }
};