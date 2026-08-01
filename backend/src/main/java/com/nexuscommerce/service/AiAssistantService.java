package com.nexuscommerce.service;

import com.nexuscommerce.dto.ai.AiAssistantRequest;
import com.nexuscommerce.dto.ai.AiAssistantResponse;

import java.util.List;
import java.util.Map;

public interface AiAssistantService {

    AiAssistantResponse processAssistantChat(AiAssistantRequest request);

    Map<String, String> getStorePolicies();

    List<Map<String, String>> getFaqs();
}
