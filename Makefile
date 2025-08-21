# =================================================================
# Makefile Auto-documentado para o Projeto Vitacare
# =================================================================

SHELL := /bin/bash
.DEFAULT_GOAL := help
.PHONY: up down stop restart status logs build rebuild clean clean-build install help

up: ## Inicia todos os serviços do docker-compose em segundo plano.
	@echo "🚀 Iniciando ambiente Vitacare..."
	@docker-compose up -d

down: ## Para e remove todos os contêineres e redes do projeto.
	@echo "🛑 Parando e removendo ambiente Vitacare..."
	@docker-compose down

stop: ## Para os contêineres, mas não os remove (início mais rápido depois).
	@echo "😴 Hibernando ambiente Vitacare..."
	@docker-compose stop

restart: ## Reinicia todos os serviços.
	@echo "🔄 Reiniciando ambiente Vitacare..."
	@docker-compose restart

build: ## Constrói ou reconstrói as imagens de todos os serviços.
	@echo "🏗️ Construindo imagens de todos os serviços..."
	@docker-compose build

rebuild: ## Reconstrói e reinicia um serviço específico. Uso: make rebuild service=<nome>
	@echo "🏗️ Reconstruindo e reiniciando o serviço: ${service}..."
	@docker-compose up -d --build --force-recreate --no-deps ${service}

clean: down ## Limpeza completa: remove contêineres, redes, e imagens/cache não usados.
	@echo "🧹 Faxina completa do Docker..."
	@docker system prune -a -f

clean-build: ## ATENÇÃO: Reset total. Apaga volumes do projeto e reconstrói tudo.
	@echo "💥 Reset total do ambiente (incluindo banco de dados)..."
	@docker-compose down -v
	@echo "🧹 Faxina completa do Docker..."
	@docker system prune -a -f
	@echo "🏗️ Reconstruindo e iniciando tudo do zero..."
	@docker-compose up --build -d

status: ## Mostra o status dos contêineres do projeto.
	@docker-compose ps

logs: ## Mostra os logs de um serviço (ou todos). Uso: make logs service=<nome>
	@echo "📜 Vendo logs para: ${service}..."
	@docker-compose logs -f ${service}

install: ## Executa 'mvn clean install' na raiz do projeto.
	@echo "📦 Instalando todos os módulos Maven localmente..."
	@mvn clean install

help: ## Mostra esta mensagem de ajuda.
	@echo "Comandos disponíveis:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-20s\033[0m %s\n", $$1, $$2}'