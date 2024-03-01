package org.unisphere.unisphere.group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.unisphere.unisphere.auth.jwt.JwtTokenProvider;
import org.unisphere.unisphere.group.dto.response.GroupListResponseDto;
import org.unisphere.unisphere.utils.E2EMvcTest;
import org.unisphere.unisphere.utils.JsonMatcher;
import org.unisphere.unisphere.utils.PersistenceHelper;
import org.unisphere.unisphere.utils.entity.TestGroup;
import org.unisphere.unisphere.utils.entity.TestMember;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GroupReadE2ETest extends E2EMvcTest {

	private final String BASE_URL = "/api/v1/groups";
	private PersistenceHelper persistenceHelper;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	private String token;

	@BeforeEach
	public void setup(WebApplicationContext webApplicationContext) {
		super.setup(webApplicationContext);
		persistenceHelper = PersistenceHelper.start(em);
	}

	@Nested
	class GetAllGroups {

		private final String URL = BASE_URL + "/all";

		@BeforeEach
		public void setup() {
			loginMember = persistenceHelper.persistAndReturn(
					TestMember.asDefaultEntity()
			);
			token = jwtTokenProvider.createCommonAccessToken(loginMember.getId()).getTokenValue();
		}

		@Test
		@DisplayName("모든 그룹을 조회한다.")
		public void testGetAllGroups() throws Exception {
			// given
			long totalGroupCount = 33;
			persistenceHelper.persistAndReturn(
					TestGroup.asDefaultEntities((int) totalGroupCount, loginMember)
			);

			// when
			MockHttpServletRequestBuilder request = get(URL)
					.header(AUTHORIZATION_VALUE, BEARER_VALUE + token);
			ResultActions resultActions = mockMvc.perform(request);

			// then
			JsonMatcher response = JsonMatcher.create();
			resultActions
					.andExpect(status().isOk())
					.andExpect(
							response.get(GroupListResponseDto.Fields.totalGroupCount)
									.isEquals(totalGroupCount));
		}
	}
}
