<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="post">
        <h2 class="jh-entity-heading" data-cy="postDetailsHeading">
          <span v-text="t$('idempotencyAppPocApp.post.detail.title')"></span> {{ post.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.name')"></span>
          </dt>
          <dd>
            <span>{{ post.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.body')"></span>
          </dt>
          <dd>
            <span>{{ post.body }}</span>
          </dd>
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.createdAt')"></span>
          </dt>
          <dd>
            <span v-if="post.createdAt">{{ formatDateLong(post.createdAt) }}</span>
          </dd>
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.updatedAt')"></span>
          </dt>
          <dd>
            <span v-if="post.updatedAt">{{ formatDateLong(post.updatedAt) }}</span>
          </dd>
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.active')"></span>
          </dt>
          <dd>
            <span>{{ post.active }}</span>
          </dd>
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.tags')"></span>
          </dt>
          <dd>
            <span v-text="t$('idempotencyAppPocApp.TagEnum.' + post.tags)"></span>
          </dd>
          <dt>
            <span v-text="t$('idempotencyAppPocApp.post.user')"></span>
          </dt>
          <dd>
            {{ post.user ? post.user.login : '' }}
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="post.id" :to="{ name: 'PostEdit', params: { postId: post.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./post-details.component.ts"></script>
