

        function clickCounter() {
            if(typeof(Storage) !== "undefined") {
                if (localStorage.clickcount) {
                    localStorage.clickcount = Number(localStorage.clickcount)+1;
                } else {
                    localStorage.clickcount = 1;
                }
                document.getElementById("cart-items").innerHTML = localStorage.clickcount;
            } else {
                document.getElementById("result").innerHTML = "Sorry, your browser does not support web storage...";
            }
        }

        function clickCounterArr(id) {
            if(localStorage.arr) {
                if(localStorage.arr[id]) {
                    localStorage.arr[id] = (Number) (localStorage.arr[id]) +1;
                } else {
                    localStorage.arr[id] = 1;
                }
            } else {
                console.log("greska");
            }
        }

        let getData = async function () {
            let response = await fetch("https://wimlab2.azurewebsites.net/categories");
            let data = await response.json();
            addCategories(data);
            //localStorage.clear();
        }   

        let addCategories = async function (categories) {
            let main = document.querySelector('main');
            let categoryTemplate = document.querySelector('#category-template');

            for (let index = 0; index < categories.length; index++) {
                let category = categoryTemplate.content.cloneNode(true);
                let categoryTitleElement = category.querySelector('.decorated-title > span');
                categoryTitleElement.textContent = categories[index].name;
                let flag = false;
                let response_product = await fetch("https://wimlab2.azurewebsites.net/products/1");
                let data_product = await response_product.json();
                let productTemplate = document.querySelector('#product-template');
                let my_gallery = document.querySelector('main > div');
                let elem = document.createElement('div');
                elem.setAttribute('class' , 'gallery');
                for(let i = 0; i < data_product.length; i++) {
                    let product = productTemplate.content.cloneNode(true);
                    let photo_box_src = product.querySelector('.photo-box > img');
                    let photo_box = product.querySelector('.photo-box');
                    let photo_box_name = product.querySelector('.photo-box > .photo-box-title');
                    let photo_box_cart = product.querySelector('.photo-box > .cart-btn');
                    if(data_product[i].categoryId === categories[index].id) {
                        flag = true;
                        photo_box.setAttribute('data-id', data_product[i].id);
                        photo_box_name.textContent = data_product[i].name;
                        photo_box_src.setAttribute('src', data_product[i].imageUrl);
                        photo_box_cart.setAttribute('onclick', "clickCounterArr(data_product[i].id)");
                        elem.appendChild(product);
                    }
                }
                if(flag == true) {
                    //console.log("tu");
                    main.appendChild(category);
                    main.appendChild(elem);
                    //flag = true;
                }
                
            }
        };
        getData();
		
