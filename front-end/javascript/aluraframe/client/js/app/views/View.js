class View {
    constructor(elemento) {
        this._elemento = elemento;
    }

    template() { // programação defensiva!
        throw new Error('método template deve ser implementado');
    }

    update(model) {
        this._elemento.innerHTML = this.template(model);
    }
}
