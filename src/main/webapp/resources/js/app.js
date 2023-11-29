document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }

  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      // finding all data to put into summary
      const categories = document.querySelectorAll('[name="categories"]:checked');
      const bags = document.querySelector('#quantity');
      const foundation = document.querySelector('[name="institution"]:checked');
      const street = document.querySelector('[name="street"]');
      const city = document.querySelector('[name="city"]');
      const zipCode = document.querySelector('[name="zipCode"]');
      const phoneNumber = document.querySelector('[name="phoneNumber"]');
      const pickUpDate = document.querySelector('[name="pickUpDate"]');
      const pickUpTime = document.querySelector('[name="pickUpTime"]');
      const pickUpComment = document.querySelector('[name="pickUpComment"]');

      // finding all fields in summary
      const summaryText = document.querySelectorAll('.summary--text');
      const formSectionColumn = document.querySelectorAll('.form-section--column');

      // setting up values into summary
      if( bags.value > 1){
        summaryText.item(0).innerText = bags.value + " worki ";
      } else {
        summaryText.item(0).innerText = bags.value + " worek ";
      }
      categories.forEach(category => {
        summaryText.item(0).innerText += " " + category.parentElement.querySelector(".description").innerText;
      });
      summaryText.item(1).innerText = "Dla fundacji " + foundation.parentElement.querySelector('.title').innerText;

      const address = [document.createElement('li'),document.createElement('li'),document.createElement('li'),document.createElement('li')]
      address.at(0).innerText = street.value;
      address.at(1).innerText = city.value;
      address.at(2).innerText = zipCode.value;
      address.at(3).innerText = phoneNumber.value;
      const h4 = document.createElement('h4');
      h4.innerText = 'Adres odbioru:';
      const ul = document.createElement('ul');

      formSectionColumn.item(2).innerHTML = '';
      formSectionColumn.item(2).appendChild(h4);
      address.forEach(el => ul.appendChild(el));
      formSectionColumn.item(2).appendChild(ul);

      const pickUp = [document.createElement('li'),document.createElement('li'),document.createElement('li')];
      pickUp[0].innerText = pickUpDate.value;
      pickUp[1].innerText = pickUpTime.value;
      pickUp[2].innerText = pickUpComment.value;
      const h42 = document.createElement('h4');
      h42.innerText = 'Termin odbioru:';
      const ul2 = document.createElement('ul');
      formSectionColumn.item(3).innerHTML = '';
      formSectionColumn.item(3).appendChild(h42);
      pickUp.forEach(el => ul2.appendChild(el));
      formSectionColumn.item(3).appendChild(ul2);

    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }

  // Are both passwords the same validation
  const registrationButton = document.querySelector("#register");
  registrationButton.addEventListener("click",e =>{
    e.preventDefault();
    const password = document.querySelector("#original");
    const confirmPassword = document.querySelector("#confirm");
    if( password.value === confirmPassword.value){
      document.querySelector("#password-form").submit();
    } else {
      if( !confirmPassword.parentElement.querySelector('span')){
        const span = document.createElement("span");
        span.innerText = "Hasła nie są identyczne";
        span.classList.add('fail');
        confirmPassword.parentElement.appendChild(span);
      }
    }
  });
});
