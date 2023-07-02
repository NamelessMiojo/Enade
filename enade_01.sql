-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 01/07/2023 às 05:12
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28



SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `enade`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `prova`
--

CREATE TABLE `prova` (
  `ID_PROVA` int(11) NOT NULL,
  `DATA_PROVA` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `prova`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `prova_aluno`
--

CREATE TABLE `prova_aluno` (
  `ID_PROVA` int(11) NOT NULL,
  `ID_QUESTAO` int(11) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  `ALTERNATIVA` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `prova_aluno`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `prova_questao`
--

CREATE TABLE `prova_questao` (
  `ID_PROVA` int(11) NOT NULL,
  `ID_QUESTAO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `prova_questao`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `questao`
--

CREATE TABLE `questao` (
  `ID_QUESTAO` int(11) NOT NULL,
  `DESCRICAO_QUESTAO` varchar(45) NOT NULL,
  `ALTERNATIVA_A` varchar(45) NOT NULL,
  `ALTERNATIVA_B` varchar(45) NOT NULL,
  `ALTERNATIVA_C` varchar(45) NOT NULL,
  `ALTERNATIVA_D` varchar(45) NOT NULL,
  `ALTERNATIVA_E` varchar(45) NOT NULL,
  `QUESTAO_CORRETA` varchar(45) NOT NULL,
  `ESTADO_QUESTAO` tinyint(4) NOT NULL,
  `ID_TIPO_QUESTAO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `questao`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `resultado`
--

CREATE TABLE `resultado` (
  `ID_RESULTADO` int(11) NOT NULL,
  `VALOR_OBTIDO` double NOT NULL,
  `ACERTOS` int(11) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  `ID_PROVA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `resultado`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `tipo_questao`
--

CREATE TABLE `tipo_questao` (
  `ID_TIPO_QUESTAO` int(11) NOT NULL,
  `NOME_TIPO_QUESTAO` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tipo_questao`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `ID_TIPO_USUARIO` int(11) NOT NULL,
  `NOME_TIPO_USUARIO` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tipo_usuario`
--


-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `ID_USUARIO` int(11) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `SENHA` varchar(255) NOT NULL,
  `ID_TIPO_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--


--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `prova`
--
ALTER TABLE `prova`
  ADD PRIMARY KEY (`ID_PROVA`);

--
-- Índices de tabela `prova_aluno`
--
ALTER TABLE `prova_aluno`
  ADD PRIMARY KEY (`ID_PROVA`,`ID_QUESTAO`,`ID_USUARIO`),
  ADD KEY `FK_PROVA_ALUNO_USUARIO` (`ID_USUARIO`),
  ADD KEY `FK_PROVA_ALUNO_QUESTAO` (`ID_QUESTAO`);

--
-- Índices de tabela `prova_questao`
--
ALTER TABLE `prova_questao`
  ADD PRIMARY KEY (`ID_PROVA`,`ID_QUESTAO`);

--
-- Índices de tabela `questao`
--
ALTER TABLE `questao`
  ADD PRIMARY KEY (`ID_QUESTAO`),
  ADD KEY `FK_TIPO_QUESTAO_QUESTAO` (`ID_TIPO_QUESTAO`);

--
-- Índices de tabela `resultado`
--
ALTER TABLE `resultado`
  ADD PRIMARY KEY (`ID_RESULTADO`),
  ADD UNIQUE KEY `ID_USUARIO` (`ID_USUARIO`,`ID_PROVA`),
  ADD KEY `FK_PROVA_RESULTADO` (`ID_PROVA`);

--
-- Índices de tabela `tipo_questao`
--
ALTER TABLE `tipo_questao`
  ADD PRIMARY KEY (`ID_TIPO_QUESTAO`);

--
-- Índices de tabela `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`ID_TIPO_USUARIO`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_USUARIO`),
  ADD KEY `FK_TIPO_USUARIO_USUARIO` (`ID_TIPO_USUARIO`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `prova`
--
ALTER TABLE `prova`
  MODIFY `ID_PROVA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `questao`
--
ALTER TABLE `questao`
  MODIFY `ID_QUESTAO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `resultado`
--
ALTER TABLE `resultado`
  MODIFY `ID_RESULTADO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `tipo_questao`
--
ALTER TABLE `tipo_questao`
  MODIFY `ID_TIPO_QUESTAO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `ID_TIPO_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `prova_aluno`
--
ALTER TABLE `prova_aluno`
  ADD CONSTRAINT `FK_PROVA_ALUNO_PROVA` FOREIGN KEY (`ID_PROVA`) REFERENCES `prova` (`ID_PROVA`),
  ADD CONSTRAINT `FK_PROVA_ALUNO_QUESTAO` FOREIGN KEY (`ID_QUESTAO`) REFERENCES `questao` (`ID_QUESTAO`),
  ADD CONSTRAINT `FK_PROVA_ALUNO_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID_USUARIO`);

--
-- Restrições para tabelas `questao`
--
ALTER TABLE `questao`
  ADD CONSTRAINT `FK_TIPO_QUESTAO_QUESTAO` FOREIGN KEY (`ID_TIPO_QUESTAO`) REFERENCES `tipo_questao` (`ID_TIPO_QUESTAO`);

--
-- Restrições para tabelas `resultado`
--
ALTER TABLE `resultado`
  ADD CONSTRAINT `FK_PROVA_RESULTADO` FOREIGN KEY (`ID_PROVA`) REFERENCES `prova` (`ID_PROVA`),
  ADD CONSTRAINT `FK_USUARIO_RESULTADO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID_USUARIO`);

--
-- Restrições para tabelas `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_TIPO_USUARIO_USUARIO` FOREIGN KEY (`ID_TIPO_USUARIO`) REFERENCES `tipo_usuario` (`ID_TIPO_USUARIO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
