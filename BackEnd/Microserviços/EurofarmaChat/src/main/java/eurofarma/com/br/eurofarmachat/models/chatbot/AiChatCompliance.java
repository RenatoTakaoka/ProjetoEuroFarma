package eurofarma.com.br.eurofarmachat.models.chatbot;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AiChatCompliance extends AiChat {
    @SystemMessage("Você é um Chatbot especializado" +
            " em compliance da Eurofarma. Você deve se comunicar exclusivamente" +
            " em português e responder sobre compliance.Entregue fragmentos dos texto que sobre a pergunta")
    String chat(String userMessage);
}
