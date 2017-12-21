class ArquivoController {

    constructor() {
        this._inputDados = document.querySelector('.dados-arquivo');
    }

    envia() {
        if (this._validaCampo(this._inputDados.value)) {
            let arquivo = new Arquivo(...this._separaCampo());
            this._limpaFormulario();
            console.log(arquivo);
        } else {
            throw new Error("Campo invalido!");
        }
    }

    _separaCampo() {
        return this._inputDados.value.split(" ");
    }

    _validaCampo(campo) {
        return /^[A-Z+0-9]/.test(campo);
    }

    _limpaFormulario() {
        this._inputDados.value = '';
        this._inputDados.focus();
    }
}
