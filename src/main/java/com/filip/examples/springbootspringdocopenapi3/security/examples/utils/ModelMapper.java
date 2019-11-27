package com.filip.examples.springbootspringdocopenapi3.security.examples.utils;

import com.filip.examples.springbootspringdocopenapi3.security.examples.models.Poll;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.ChoiceResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PollResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.UserSummary;
import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;
import org.apache.tomcat.jni.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, ApplicationUser creator, Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreationDate());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        LocalDateTime now = LocalDateTime.now();
        pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());

            if(choiceVotesMap.containsKey(choice.getId())) {
                choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
            } else {
                choiceResponse.setVoteCount(0);
            }
            return choiceResponse;
        }).collect(Collectors.toList());

        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreatedBy(creatorSummary);

        if(userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }
}
